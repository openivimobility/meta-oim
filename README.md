# meta-oim

## Build a yocto release with Openivi-html

See https://openivimobility.github.io/

### A note for ArchLinux users
ArchLinux uses GCC 6.x.x which is quite new and has some problems with older software (or the older software has problems with it). Moreover, when installing Arch on x64, only GCC for 64-bit machines will be installed, to compile for 32-bit architectures or even launch binaries, that were compiled for i686 32-bit libraries and "multilib" version of GCC should be installes. Finally, GCC is downgraded together with libstdc++ and clang also uses it, so clang and LLVM should be downgraded as well. These packages seem to work well:

    mkdir ~/arch-packages
    cd ~/arch-packages
    wget https://archive.archlinux.org/packages/g/gcc-multilib/gcc-multilib-5.3.0-1-x86_64.pkg.tar.xz \
        https://archive.archlinux.org/packages/g/gcc-libs-multilib/gcc-libs-multilib-5.3.0-1-x86_64.pkg.tar.xz \
        https://archive.archlinux.org/packages/l/lib32-gcc-libs/lib32-gcc-libs-5.3.0-1-x86_64.pkg.tar.xz \
        https://archive.archlinux.org/packages/c/clang/clang-3.7.0-4-x86_64.pkg.tar.xz \
        https://archive.archlinux.org/packages/l/lib32-clang/lib32-clang-3.7.0-4-x86_64.pkg.tar.xz \
        https://archive.archlinux.org/packages/l/llvm/llvm-3.7.0-4-x86_64.pkg.tar.xz \
        https://archive.archlinux.org/packages/l/llvm-libs/llvm-libs-3.7.0-4-x86_64.pkg.tar.xz \
        https://archive.archlinux.org/packages/l/lib32-llvm/lib32-llvm-3.7.0-4-x86_64.pkg.tar.xz \
        https://archive.archlinux.org/packages/l/lib32-llvm-libs/lib32-llvm-libs-3.7.0-4-x86_64.pkg.tar.xz
    sudo pacman -U gcc-multilib-5.3.0-1-x86_64.pkg.tar.xz \
        gcc-libs-multilib-5.3.0-1-x86_64.pkg.tar.xz \
        lib32-gcc-libs-5.3.0-1-x86_64.pkg.tar.xz \
        clang-3.7.0-4-x86_64.pkg.tar.xz \
        lib32-clang-3.7.0-4-x86_64.pkg.tar.xz \
        llvm-3.7.0-4-x86_64.pkg.tar.xz \
        llvm-libs-3.7.0-4-x86_64.pkg.tar.xz \
        lib32-llvm-3.7.0-4-x86_64.pkg.tar.xz \
        lib32-llvm-libs-3.7.0-4-x86_64.pkg.tar.xz
        
pacman will report conflicts with installed versions of gcc and gcc-libs, just proceed.

## Making a bootable SD card
Make sure that your card is not used and unmounted. Then run, replacing ${SD\_DEV} with the device corresponding to SD card on your host machine, i.e. /dev/sdc. /dev/mmcblk0 is the device name on the target machine, leave it as is.

    sudo ../meta-oim/scripst/mkefidisk.sh ${SD_DEV} tmp/deploy/images/genericx86-64/openivi-image-genericx86-64.hddimg /dev/mmcblk0

This may take about 10 minutes.

## Running QEMU emulation

Run 

    runqemu qemux86

or

    runqemu qemux86-64

depending on the MACHINE set in local.conf on building

