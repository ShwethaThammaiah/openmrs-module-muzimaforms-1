basePath = '../../../';

files = [
  JASMINE,
  JASMINE_ADAPTER,
  'main/webapp/resources/js/angular/angular.min.js',
  'main/webapp/resources/js/angular/angular-resource.js',
  'test/js/lib/angular/angular-mocks.js',
  'main/webapp/resources/js/custom/*.js',
  'test/js/unit/**/*.js'
];

autoWatch = true;

browsers = ['Chrome'];

junitReporter = {
  outputFile: 'test_out/unit.xml',
  suite: 'unit'
};
