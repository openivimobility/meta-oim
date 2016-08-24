SUMMARY = "Main OpenIVI image"

IMAGE_FEATURES += "splash package-management x11-base x11-sato ssh-server-dropbear hwcodecs"

LICENSE = "MIT"

inherit core-image
inherit image-repo-manifest

APPEND+="console=ttyS0,115200 quiet"

# We don't support/test the ISO image, so don't build it
NOISO = "1"

# In 512 byte blocks
BOOTIMG_EXTRA_SPACE = "2000000"

# In kb
IMAGE_ROOTFS_EXTRA_SPACE = "100000"

IMAGE_INSTALL_append = " \
	kernel-modules \
	linux-firmware \
	connman \
	connman-tools \
	connman-client \
	qtbase \
	qtbase-plugins \
	html5-headunit \
	openivi-html5 \
	ota-plus-demo-provision \
	sota-client \
	xinput \
	tor \
	ssh-keys \
	"

# If debug-tweaks is set, don't start the tor service automatically, because it
# exposes ssh to the world, and debug-tweaks allows no-password root logins
IMAGE_INSTALL_append = "${@bb.utils.contains('EXTRA_IMAGE_FEATURES', 'debug-tweaks', '', 'tor-config', d)}"
