#!/bin/sh

# USAGE

#
# export AWS_ACCESS_KEY_ID=abc123
# export AWS_SECRET_ACCESS_KEY=def456
#
# `release.sh ../../build/tmp/deploy/images/genericx86-64/openivi-image-genericx86-64.iso`
# This will save the following on s3:
#   - openivi-releases/YYYYMMDD/openivi-image.zip
#   - openivi-releases/YYYYMMDD/openivi-image.manifest
#   - openivi-releases/YYYYMMDD/repo-manifest.xml
#
# The script also takes a second parameter, if you want to give the image name some extra detail,
# eg. for release candidates and whatnot:
#
# `release.sh tmp/deploy/images/genericx86-64/openivi-image-genericx86-64.iso RC1`
# This will save the following on s3:
#   - openivi-releases/YYYYMMDD/openivi-image-RC1.zip
#   - openivi-releases/YYYYMMDD/openivi-image-RC1.manifest
#   - openivi-releases/YYYYMMDD/repo-manifest-RC1.zip

IMAGE_PATH=$1
IMAGE_NAME=$(basename $IMAGE_PATH)
IMAGE_DIR=$(dirname $IMAGE_PATH)
MANIFEST_FILE=${IMAGE_NAME%.*}.manifest
TODAY=$(date +%Y%m%d)

cd $IMAGE_DIR

if [ ! -z "$2" ]
then
  ZIP_NAME=openivi-image-$2.zip
  MANIFEST_FILE_S3=${IMAGE_NAME%.*}-$2.manifest
  REPO_MANIFEST_FILE=repo-manifest-$2.xml
else
  ZIP_NAME=openivi-image.zip
  MANIFEST_FILE_S3=${IMAGE_NAME%.*}.manifest
  REPO_MANIFEST_FILE=repo-manifest.xml
fi

echo $ZIP_NAME

repo manifest -r > $REPO_MANIFEST_FILE

zip -9 $ZIP_NAME $IMAGE_NAME

alias aws-cli="docker run --rm \
           -e AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID} \
           -e AWS_SECRET_ACCESS_KEY=${AWS_SECRET_ACCESS_KEY} \
           -e AWS_DEFAULT_REGION=eu-central-1 \
           -v $(pwd):/root \
           advancedtelematic/aws-cli:latest"

aws-cli s3 cp $ZIP_NAME s3://openivi-releases/$TODAY/
aws-cli s3 cp $MANIFEST_FILE s3://openivi-releases/$TODAY/$MANIFEST_FILE_S3
aws-cli s3 cp $REPO_MANIFEST_FILE s3://openivi-releases/$TODAY/

rm $REPO_MANIFEST_FILE
rm $ZIP_NAME
