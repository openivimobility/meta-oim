SUMMARY = "Main Jaspar image"

IMAGE_FEATURES += "splash package-management x11-base x11-sato hwcodecs"

LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL += " \
	packagegroup-core-x11-sato-base \
  openssh \
	matchbox-terminal \
	kernel-modules linux-firmware \
	connman connman-tools connman-client \
	bluez5-testtools \
	epiphany jaspar wds \
	gmediarender gupnp-tools \
	matchbox-wm matchbox-panel-2 \
	"
