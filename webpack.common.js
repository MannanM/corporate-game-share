const path = require('path');

module.exports = {
  entry: {
    app: './src/main/js/App.js',
  },
  output: {
      path: path.resolve(__dirname, 'src/main/resources/static/built'),
      publicPath: '/built',
      filename: 'bundle.js'
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