SUMMARY = "P8-4S Set Default CPU VID"
DESCRIPTION = "P8-4S Set Default CPU VID"
PR = "r1"

inherit obmc-phosphor-systemd
inherit obmc-phosphor-license

RDEPENDS_${PN} += "i2c-tools bash"

S = "${WORKDIR}"
SRC_URI += "file://p8-4s-set-vid.sh"

do_install() {
        install -d ${D}${bindir}
        install -m 0755 ${WORKDIR}/p8-4s-set-vid.sh ${D}${bindir}/p8-4s-set-vid.sh
}

TMPL = "p8-4s-set-vid@.service"
INSTFMT = "p8-4s-set-vid@{0}.service"
TGTFMT = "obmc-chassis-poweron@{0}.target"
FMT = "../${TMPL}:${TGTFMT}.wants/${INSTFMT}"

SYSTEMD_SERVICE_${PN} += "${TMPL}"
SYSTEMD_LINK_${PN} += "${@compose_list(d, 'FMT', 'OBMC_CHASSIS_INSTANCES')}"
