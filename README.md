# meta-oim

## Build a yocto release with Openivi-html

    git clone -b dizzy http://git.yoctoproject.org/git/poky
    cd poky
    git clone -b dizzy git@github.com:meta-qt5/meta-qt5.git
    git clone -b dizzy git://git.yoctoproject.org/meta-intel
    git clone https://github.com/openivimobility/meta-oim.git
    source oe-init-build-env

Now replace `build/conf/bblayers.conf` with `bblayers.conf` from this repo. This will add the `meta-oim` layer and the dependencies on the layers `meta-intel`, `meta-qt5`.

After that run:

    bitbake -k core-image-sato

This will take a while (1+ hours). When that completes, you can run:

    runqemu qemux86
