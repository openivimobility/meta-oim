# meta-oim

## Build a yocto release with Openivi-html

    git clone -b dizzy http://git.yoctoproject.org/git/poky
    cd poky
    git clone -b dizzy https://github.com/meta-qt5/meta-qt5.git
    git clone -b dizzy git://git.yoctoproject.org/meta-intel
    git clone https://github.com/openivimobility/meta-oim.git
    export TEMPLATECONF=meta-oim/conf
    source oe-init-build-env

After that run:

    bitbake -k openivi-image

This will take a while (1+ hours). When that completes, you can run:

    runqemu qemux86

### A note for ArchLinux users
ArchLinux uses GCC 6.x.x which is quite new and has some problems with older software (or the older software has problems with it). Moreover, when installing Arch on x64, only GCC for 64-bit machines will be installed. GCC is downgraded together with libstdc++ and clang also uses it, so clang and LLVM should be downgraded as well. These packages seem to work well:

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
        
pacman will report conflicts with install gcc and standard library, just proceed.
