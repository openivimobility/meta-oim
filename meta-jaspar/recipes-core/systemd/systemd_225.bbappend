EXTRA_OECONF_pn_systemd := "${@oe_filter_out('--disable-coredump', '${EXTRA_OECONF}', d)}"

FILES_pn_systemd += " /usr/bin/coredumpctl "

PR = "r3"
