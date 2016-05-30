SUMMARY = "Matchbox virtual keyboard for X11"
HOMEPAGE = "http://matchbox-project.org"
BUGTRACKER = "http://bugzilla.yoctoproject.org/"
SECTION = "x11"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://src/matchbox-keyboard.h;endline=17;md5=2c546c012c6a1283bc7f71219baf10f6 \
                    file://applet/applet.c;endline=18;md5=4a0f721724746b14d95b51ddd42b95e7"

DEPENDS = "libfakekey expat libxft gtk+ matchbox-panel-2"

EXTERNALSRC = "${THISDIR}/../../../../client/matchbox-keyboard"

PV = "0.2"
PR = "r0"

inherit cmake externalsrc

# The libxft, libfakekey and matchbox-panel-2 requires x11 in DISTRO_FEATURES
REQUIRED_DISTRO_FEATURES = "x11"

# These packages a provided by the recipe in sato that this package overlays
# Without these, bitbake will try and pull in the sato package too
PACKAGES += "${PN}-im ${PN}-applet"

FILES_${PN} = "${bindir}/ ${libdir}/matchbox-keyboard"

FILES_${PN}-dbg += "${libdir}/gtk-2.0/*/immodules/.debug"

FILES_${PN}-im = "${libdir}/gtk-2.0/*/immodules/*.so"

FILES_${PN}-applet = "${libdir}/matchbox-panel/*.so"

RDEPENDS_${PN} = "formfactor dbus-wait"
