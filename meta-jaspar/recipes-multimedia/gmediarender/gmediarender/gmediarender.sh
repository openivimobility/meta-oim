#!/bin/sh
#
# Script to launch gmediarender
#

connmanctl enable wifi
connmanctl enable p2p

UUID=`ip link show | awk '/ether/ {print "salt:)-" $2}' | head -n 1 | md5sum | awk '{print $1}'` \

# Set advertisement IP address of gmediarender to 127.0.0.1. The default
# behaviour is to pick the first IP address that is configured.  Since the
# network topology will change all the time as the user switches use cases
# this is not a good default. When advertising an uproutable ip address it will
# not be possible to initiate playback from the phone. It is possible
# to start playback from the IVI system, however.
#
# The log file is given as '-'. This is not a standard option in gmediarender.
# The version that will pull in has a 7 line patch to Log_init() to respect
# this option.

exec /usr/bin/gmediarender -f "Jaspar" -u $UUID \
	-I 127.0.0.1 \
	--gstout-audiosink=alsasink \
	--gstout-initial-volume-db=-10 \
	--logfile -
