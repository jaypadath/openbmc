SUMMARY = "Enable USB ethernet"
PR = "r1"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS += "systemd"
RDEPENDS_${PN} += "libsystemd"
RDEPENDS_${PN} += "bash"

inherit allarch systemd

SRC_URI += "file://usb_network.service \
            file://00-bmc-usb0.network"

do_install() {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/usb_network.service ${D}${systemd_unitdir}/system

    install -d ${D}${sysconfdir_native}/systemd/network/
    install -m 0644 ${WORKDIR}/00-bmc-usb0.network ${D}${sysconfdir_native}/systemd/network
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "usb_network.service"