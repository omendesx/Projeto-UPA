$ErrorActionPreference = "Stop"

$Root = Split-Path -Parent $MyInvocation.MyCommand.Path
$Pom = Join-Path $Root "backend\pom.xml"
$App = Join-Path $Root "backend\src\main\java\com\sistemaupa\SistemaUpaApplication.java"

if (-not (Test-Path $Pom)) {
    throw "pom.xml não encontrado em backend."
}

if (-not (Test-Path $App)) {
    throw "SistemaUpaApplication.java não encontrado."
}

Write-Host "Estrutura correta." -ForegroundColor Green
Write-Host "Abra a pasta Projeto-UPA-Limpo inteira no VS Code."
Write-Host "O projeto Maven está dentro da pasta backend."
