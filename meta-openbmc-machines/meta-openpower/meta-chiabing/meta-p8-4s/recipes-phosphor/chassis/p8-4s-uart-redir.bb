SUMMARY = "P8-4S Redirect UART4 to Port 1"
DESCRIPTION = "P8-4S Redirect UART4 to Port 1"
PR = "r1"

inherit obmc-phosphor-systemd
inherit obmc-phosphor-license

RDEPENDS_${PN} += "bash"

S = "${WORKDIR}"
SRC_URI += "file://p8-4s-uart-redir.sh"

do_install() {
        install -d ${D}${bindir}
        install -m 0755 ${WORKDIR}/p8-4s-uart-redir.sh ${D}${bindir}/p8-4s-uart-redir.sh
}

TMPL = "p8-4s-uart-redir@.service"
INSTFMT = "p8-4s-uart-redir@{0}.service"
TGTFMT = "obmc-chassis-poweron@{0}.target"
FMT = "../${TMPL}:${TGTFMT}.wants/${INSTFMT}"

SYSTEMD_SERVICE_${PN} += "${TMPL}"
SYSTEMD_LINK_${PN} += "${@compose_list(d, 'FMT', 'OBMC_CHASSIS_INSTANCES')}"
