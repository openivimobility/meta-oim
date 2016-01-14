# meta-oim

## Build a yocto release with Openivi-html

    git clone -b dizzy http://git.yoctoproject.org/git/poky
    cd poky
    git clone -b dizzy git@github.com:meta-qt5/meta-qt5.git
    git clone -b dizzy git://git.yoctoproject.org/meta-intel
    git clone git@github.com:openivimobility/meta-oim.git
    export TEMPLATECONF=meta-oim/conf
    source oe-init-build-env

After that run:

    bitbake -k openivi-image

This will take a while (1+ hours). When that completes, you can run:

    runqemu qemux86
