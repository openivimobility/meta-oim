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


    sudo apt-get install texinfo libsdl-dev chrpath -y

    git clone -b jethro http://git.yoctoproject.org/git/poky
    cd poky
    git clone -b jethro git://git.yoctoproject.org/meta-intel
    git clone -b jethro git://git.openembedded.org/meta-openembedded
    git clone git@github.com:openivimobility/meta-oim.git
    export TEMPLATECONF=meta-oim/meta-jaspar/conf
    source oe-init-build-env

Add the following to your `poky/build/conf/local.conf`, unless you
want your computer to be unusable:

    BB_NUMBER_THREADS = "1"

and the following two lines to allow more caching between bakes
(make sure the directories exist and the right permissions are set):

    DL_DIR ?= "/opt/poky-downloads/downloads"
    SSTATE_DIR ?= "/opt/poky-sstate-cache"

and then:

    bitbake -k jaspar-image

This will build a genericx86-64 image, which you can `dd` to a usb and run. 

To run it in qemu:

    sudo qemu-system-x86_64 -hda tmp/deploy/images/genericx86-64/jaspar-image-genericx86-64.iso \
        -m 1G -usb -usbdevice host:0cf3:9271 -usbdevice host:054c:06c1 -cpu Haswell -enable-kvm
