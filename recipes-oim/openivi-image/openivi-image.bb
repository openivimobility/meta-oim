SUMMARY = "Main OpenIVI image"

IMAGE_FEATURES += "splash package-management x11-base x11-sato ssh-server-dropbear hwcodecs"

LICENSE = "MIT"

inherit core-image

APPEND+="console=ttyS0,115200 quiet"

IMAGE_INSTALL += " \
	kernel-modules \
	linux-firmware \
	connman \
	connman-tools \
	connman-client \
	html5-headunit \
	openivi-html5 \
	ota-plus-demo-provision \
	ota-plus-client \
	xinput \
	"
