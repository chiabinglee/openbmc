#!/bin/sh
# 
# Script to redirect UART4 to Port 1
#
# Assumptions:
#
#     devmem 0x1e78909c 32 0x02010023
#

echo "P8-4S: redirect UART4 to Port 1 Starting"

# Redirect UART4 to Port 1
devmem 0x1e78909c 32 0x02010023

echo "P8-4S: redirect UART4 to Port 1 Finished"
