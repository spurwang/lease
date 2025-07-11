#!/bin/bash
set -e

echo "📥 拉取最新代码..."
git pull origin main

echo "🛑 停止现有服务..."
docker-compose down

echo "🔧 构建服务..."
docker-compose build

echo "🚀 启动服务..."
docker-compose up -d

echo "✅ 部署完成！"
