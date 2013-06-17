basePath = '../../../';

files = [
  JASMINE,
  JASMINE_ADAPTER,
  'main/webapp/resources/js/angular/angular.js',
  'main/webapp/resources/js/angular/angular-resource.js',
  'main/webapp/resources/js/angular/ui-bootstrap-0.3.0.js',
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
