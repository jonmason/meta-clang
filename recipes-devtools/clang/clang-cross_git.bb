# Copyright (C) 2014 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Cross compiler wrappers for LLVM based C/C++ compiler"
HOMEPAGE = "http://clang.llvm.org/"
LICENSE = "NCSA"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/NCSA;md5=1b5fdec70ee13ad8a91667f16c1959d7"
SECTION = "devel"

require clang.inc
inherit cross
DEPENDS += "clang-native binutils-cross-${TARGET_ARCH}"

S = "${WORKDIR}"

do_install() {
        install -d  ${D}${bindir_crossscripts}/
        ln -sf ../clang ${D}${bindir_crossscripts}/${TARGET_PREFIX}clang
        ln -sf ../clang++ ${D}${bindir_crossscripts}/${TARGET_PREFIX}clang++
}

SYSROOT_PREPROCESS_FUNCS += "clangcross_sysroot_preprocess"

clangcross_sysroot_preprocess () {
        sysroot_stage_dir ${D}${bindir_crossscripts} ${SYSROOT_DESTDIR}${bindir}
}
SSTATE_SCAN_FILES += "*-clang *-clang++"
PACKAGES = ""
