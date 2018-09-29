const path = require('path');

module.exports = {
  entry: './src/main/js/App.js',
  output: {
      path: path.resolve(__dirname, 'src/main/resources/static/built'),
      publicPath: '/built',
      filename: 'bundle.js'
  },
  devtool: "source-map",
  devServer: {
    port: 8081,
    contentBase: false
    //path.join(__dirname, 'src/main/resources/static'),
    //proxy: {
    //  '/v1': 'http://localhost:8080'
    //}
  },
  module: {
   rules: [
       {
         test: /\.m?js$/,
         exclude: /(node_modules|bower_components)/,
         use: {
           loader: 'babel-loader',
           options: {
             presets: ['@babel/preset-env']
           }
         }
       }
    ]
  }
};