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
	"
