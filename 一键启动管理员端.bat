@echo off
title 启动用户端服务

:: 直接切换路径并启动，不再干预系统编码
start "用户端前端服务" cmd /k "cd /d C:\Users\12460\Desktop\诊断系统\pett\springboot002\src\main\resources\admin\admin && npm run dev"

exit