SUMMARY = "Main OpenIVI image"

IMAGE_FEATURES += "splash package-management x11-base x11-sato ssh-server-dropbear hwcodecs"

LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL += " \
	kernel-modules \
	linux-firmware \
	connman \
	connman-tools \
	connman-client \
	html5-headunit \
	openivi-html5 \
	"
