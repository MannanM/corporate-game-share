const merge = require('webpack-merge');
const common = require('./webpack.common.js');

module.exports = merge(common, {
  mode: 'development',
  devtool: 'inline-source-map',
  devServer: {
    port: 8081,
    contentBase: false
    //path.join(__dirname, 'src/main/resources/static'),
    //proxy: {
    //  '/v1': 'http://localhost:8080'
    //}
  }
});