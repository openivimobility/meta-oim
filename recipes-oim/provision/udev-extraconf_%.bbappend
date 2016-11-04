# udev-extraconf creates output in /etc rather than /usr/lib/udev, so we can't
# easily override them. Instead bbappend and delete the rule
do_install_append() {
    rm ${D}${sysconfdir}/udev/rules.d/automount.rules
}
