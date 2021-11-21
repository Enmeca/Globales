module.exports = {
    devServer: {
        port: 8002,
        proxy: {
            '/api/v1': {
                target: 'http://localhost:9090',
                ws: true,
                changeOrigin: true,
            }
        }
    },
}