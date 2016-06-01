# Writes the repo manifest to the target filesystem in /etc/manifest.xml
#
# Author: Phil Wise <phil@advancedtelematic.com>
# Usage: add "inherit image-repo-manifest" to your image file
# To reproduce a build, copy the /etc/manifest.xml to .repo/manifests/yourname.xml
# then run:
# repo init -m yourname.xml
# repo sync
# See https://wiki.cyanogenmod.org/w/Doc:_Using_manifests for more information

# Write build information to target filesystem
buildinfo () {
  repo manifest --revision-as-HEAD -o ${IMAGE_ROOTFS}${sysconfdir}/manifest.xml
}


IMAGE_PREPROCESS_COMMAND += "buildinfo;"
