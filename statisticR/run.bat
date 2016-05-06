@echo off
mkdir rlibrary
setlocal
set path=%Path%;%ProgramFiles%\R\R-3.2.3\bin\x64\
rscript statistic.r
pause