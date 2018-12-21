#!/bin/bash
#
# This script build & release current module.
#
# Author: ZHANG Li Dan @ Enlink DataCenter.
# Copyright 2018 (c) Enlink CO. LTD.


APP_NAME="elasticsearch"
APP_PKG=""
APP_PLUGINS=()
APP_JDK=""

DIR_CURRENT=`pwd`
DIR_RELEASE=${DIR_CURRENT}
DIR_PLUGINS="${DIR_CURRENT}/plugins"
DIR_PKG="${DIR_CURRENT}/pkg"
DIR_JDK="${DIR_CURRENT}/jdk"
DIR_CFG="${DIR_CURRENT}/cfg"


# selectPkg() - Select elasticsearch package to release.
#
# This function stores the selected package in ${APP_PKG}.
function selectPkg() {
	echo
	echo "Select elasticsearch package from ${DIR_PKG} ..."

	while true; do
			list=(`ls ${DIR_PKG}`)
			len=${#list[@]}

			echo "There are ${len} elasticsearch package found as follows:"
			for ((cc = 0; cc < ${len}; cc ++)) do
				echo -e "  \033[01;34m${cc}  ${list[${cc}]}\033[0m"
			done

			printf "Input your selection: "
			read index

			if [ "${index}" -ge 0 ] 2>/dev/null && [ "${index}" -lt ${len} ]; then
				APP_PKG=${list[$index]}
				break
			fi

			echo -e "\033[31m[ERROR] Invalid input '${index}', please try again.\033[0m"
			echo
		done

}


# selectPlugins() - Select plugins to release.
#
# This function stores the selected plugins in ${APP_PLUGINS}.
function selectPlugins() {
	echo
	printf "Select ingest plugins from ${DIR_PLUGINS} ... (y/n): "
	read input
	
	if [ "${input}" == "y" ]; then
		for item in `ls ${DIR_PLUGINS}`; do
			printf "Select \033[01;34m%-40.40s\033[0m (y/n): " "${item}"
			read input

			if [ "${input}" == "y" ]; then
				APP_PLUGINS=(${APP_PLUGINS[*]} ${item})
			fi
		done
	fi
}


# selectJdk() - Select Java JDK to release.
#
# This function stores the selected plugins in ${APP_JDK}.
function selectJdk() {
	echo
	printf "Select Java JDK from ${DIR_JDK} ...? (y/n): "
	read input

	if [ "${input}" == "y" ]; then
		while true; do
			list=(`ls ${DIR_JDK}`)
			len=${#list[@]}

			echo "There are ${len} Java JDK found as follows:"
			for ((cc = 0; cc < ${len}; cc ++)) do
				echo -e "  \033[01;34m${cc}  ${list[${cc}]}\033[0m"
			done

			printf "Input your selection (or 'n' to skip): "
			read index

			if [ "${index}" -ge 0 ] 2>/dev/null && [ "${index}" -lt ${len} ]; then
				APP_JDK=${list[$index]}
				break
			elif [ "${index}" == "n" ]; then
				break
			fi

			echo -e "\033[31m[ERROR] Invalid input '${index}', please try again.\033[0m"
			echo
		done
	fi
}


# build() - Handle 'make build' command.
#
# Do nothing for elasticsearch release.
function build() {
	echo "Building ${APP_NAME} ... done!"
}


# release() - Handle 'make release' command.
#
# - $1: Target path to release to.
#       Use current path if $1 is empty.
function release() {
	if [ "$1" != "" ]; then
		DIR_RELEASE="$1"
	fi

	selectPkg
	selectPlugins
	selectJdk

	dir_target="${DIR_RELEASE}/${APP_NAME}"
	echo
	echo "Release ${APP_NAME} to ${DIR_RELEASE} ..."

	rm -rf ${dir_target}
	for item in cfg pkg jdk plugins; do
		mkdir -p ${dir_target}/${item}
	done

	cp -rf ${DIR_CURRENT}/install.sh ${dir_target}
	cp -rf ${DIR_CFG}/*              ${dir_target}/cfg 2>/dev/null
	cp -rf ${DIR_PKG}/${APP_PKG}     ${dir_target}/pkg

	if [ "${APP_JDK}" != "" ]; then
		cp -rf ${DIR_JDK}/${APP_JDK} ${dir_target}/jdk
	fi

	for item in ${APP_PLUGINS[@]}; do
		cp -rf ${DIR_PLUGINS}/${item} ${dir_target}/plugins
	done
}


# clean() - Handle 'make clean' command.
#
# Cleanup all temporary files or directories.
function clean() {
	echo "Cleaning up release directory (${DIR_RELEASE}/${APP_NAME}) ..."
	rm -rf ${DIR_RELEASE}/${APP_NAME}
}


# Call functions depends on the user's input.
#
# - build:   Build the module and generate the binary file.
# - release: Release (copy files) to specified directory.
#            This command will call 'build' first.
# - clean:   Cleanup temporary files or directories.
case $1 in
	"build")
		build
		;;

	"release")
		build
		release $2
		;;

	"clean")
		clean
		;;
esac

