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

## Build a jaspar image


    git clone -b jethro http://git.yoctoproject.org/git/poky
    cd poky
    git clone -b jethro git://git.yoctoproject.org/meta-intel
    git clone -b jethro git://git.openembedded.org/meta-openembedded
    git clone git@github.com:openivimobility/meta-oim.git
    export TEMPLATECONF=meta-oim/meta-jaspar/conf
    source oe-init-build-env
    sudo apt-get install texinfo libsdl-dev chrpath -y
    bitbake -k jaspar-image

This will build a genericx86-64 image, which you can `dd` to a usb and run. Currently a qemux86-64 image does not build (due to webkitgtk)
