'use strict';
angular.module('app').run(['$rootScope', '$state', '$stateParams',
  function($rootScope, $state, $stateParams) {
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;
    $rootScope.$on('$stateChangeSuccess', function() {
      window.scrollTo(0, 0);
    });
    FastClick.attach(document.body);
  },
]).config(['$stateProvider', '$urlRouterProvider', '$injector',
  function($stateProvider, $urlRouterProvider, $injector) {
    $urlRouterProvider.otherwise( function($injector) {
    	var $state = $injector.get("$state");
    	$state.go('app.landing');
    });
    
    // Application routes
    $stateProvider.state('app', {
        abstract: true,
        templateUrl: './views/common/layout.html'
      }).state('app.landing',{
    	  url: '/landing',
            templateUrl: './views/app/landing/landing.html',
            controller: 'landingCtrl',
        	resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                  return $ocLazyLoad.load([{
                      files: [
                    	  	  './scripts/services/patientServices.js',
                              './scripts/controllers/landing/landingCtrl.js'
                              ]
                    }]);
                }]
              }
      })
//      .state('user', {
//        templateUrl: './views/common/session.html',
//      }).state('user.signin', {
//        url: '/signin',
//        templateUrl: './views/signin.html',
//        controller:'sessionCtrl',
//        resolve: {
//          deps: ['$ocLazyLoad', function($ocLazyLoad) {
//            return $ocLazyLoad.load('./scripts/controllers/session.js');
//          }]
//        },
//        data: {
//          appClasses: 'signin usersession',
//          contentClasses: 'session-wrapper'
//        }
//      }).state('user.signout',{
//    	  url:'/signout',
//    	  templateUrl:'./views/signin.html',
//    	  controller:'logoutCtrl',
//          resolve: {
//              deps: ['$ocLazyLoad', function($ocLazyLoad) {
//                return $ocLazyLoad.load('./scripts/controllers/logout.js');
//              }]
//            },
//            data: {
//              appClasses: 'signin usersession',
//              contentClasses: 'session-wrapper'
//            }
//      })
      
     ;
  }
]).config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
  $ocLazyLoadProvider.config({
    debug: false,
    events: false
  });
}]);
