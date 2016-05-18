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

PV = "0.1"
PR = "r0"

inherit autotools pkgconfig gettext gtk-immodules-cache distro_features_check externalsrc

# The libxft, libfakekey and matchbox-panel-2 requires x11 in DISTRO_FEATURES
REQUIRED_DISTRO_FEATURES = "x11"

EXTRA_OECONF = "--disable-cairo --enable-gtk-im --enable-applet"

PACKAGES += "${PN}-im ${PN}-applet"

FILES_${PN} = "${bindir}/ \
	       ${sysconfdir} \
         ${libdir}/libmatchbox-keyboard.so.* \        
	       ${datadir}/applications \
	       ${datadir}/pixmaps \
	       ${datadir}/matchbox-keyboard"

FILES_${PN}-dbg += "${libdir}/gtk-2.0/*/immodules/.debug"

FILES_${PN}-im = "${libdir}/gtk-2.0/*/immodules/*.so"

FILES_${PN}-applet = "${libdir}/matchbox-panel/*.so"


do_install_append () {
	rm -f ${D}${libdir}/gtk-2.0/*/immodules/*.la
	rm -f ${D}${libdir}/matchbox-panel/*.la
}

GTKIMMODULES_PACKAGES = "${PN}-im"

RDEPENDS_${PN} = "formfactor dbus-wait"
RRECOMMENDS_${PN} = "${PN}-applet"
