module.exports = {
    devServer: {
        port: 8080,
        proxy:{
            '/api/v1': {
                target: 'http://localhost:9191',
                ws: true,
                changeOrigin: true,
            }
        }
    },
}