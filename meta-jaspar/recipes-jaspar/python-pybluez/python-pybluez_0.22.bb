DESCRIPTION = "Bluetooth Python extension module http://karulis.github.io/pybluez/"
SECTION = "devel/python"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://README;md5=db837656266d59ec3aa0fbcd772b8a4c"
PR = "6"

SRC_URI = "https://github.com/karulis/pybluez/archive/${PV}.tar.gz"

S = "${WORKDIR}/pybluez-${PV}"

inherit distutils

DEPENDS = "python"
RDEPENDS_${PN} += " python-setuptools \
                    python-pkgutil \
                    python-modules \
                  "

do_compile_prepend() {
    BUILD_SYS=${BUILD_SYS} HOST_SYS=${HOST_SYS} \
    ${STAGING_BINDIR_NATIVE}/python-native/python setup.py install || \
    true
}

# need to export these variables for python-config to work
export PYTHONPATH
export BUILD_SYS
export HOST_SYS
export STAGING_INCDIR
export STAGING_LIBDIR


SRC_URI[md5sum] = "06f71ca6d4216ace671d5e588ec85887"
SRC_URI[sha256sum] = "53db881a2668791062985e1ff7afbe6527cdd9af3676a3160420a235bee3c768"
