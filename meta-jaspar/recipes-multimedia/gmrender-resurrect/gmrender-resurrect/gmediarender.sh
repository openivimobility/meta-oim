#!/bin/sh
#
# Script to launch gmediarender
#

UUID=`ip link show | awk '/ether/ {print "salt:)-" $2}' | head -n 1 | md5sum | awk '{print $1}'` \

exec /usr/bin/gmediarender -f "Media Library" -d -u $UUID \
	--gstout-audiosink=alsasink --gstout-audiodevice=sysdevice \
	--gstout-initial-volume-db=-10
