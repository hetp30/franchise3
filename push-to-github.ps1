# GitHub Push Script
# Replace YOUR_USERNAME with your actual GitHub username

param(
    [Parameter(Mandatory=$true)]
    [string]$GitHubUsername,
    
    [Parameter(Mandatory=$false)]
    [string]$RepoName = "franchise3"
)

Write-Host "Setting up GitHub remote..." -ForegroundColor Green

# Remove existing remote if it exists
git remote remove origin 2>$null

# Add GitHub remote
git remote add origin "https://github.com/$GitHubUsername/$RepoName.git"

# Rename branch to main (if needed)
git branch -M main

Write-Host "Pushing to GitHub..." -ForegroundColor Green
git push -u origin main

if ($LASTEXITCODE -eq 0) {
    Write-Host "Successfully pushed to GitHub!" -ForegroundColor Green
    Write-Host "Repository URL: https://github.com/$GitHubUsername/$RepoName" -ForegroundColor Cyan
} else {
    Write-Host "Push failed. Make sure:" -ForegroundColor Red
    Write-Host "1. Repository exists on GitHub: https://github.com/$GitHubUsername/$RepoName" -ForegroundColor Yellow
    Write-Host "2. You're authenticated (GitHub may prompt for credentials)" -ForegroundColor Yellow
}
