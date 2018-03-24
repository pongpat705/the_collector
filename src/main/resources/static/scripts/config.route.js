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
      }).state('app.cashbook',{
    	  url: '/cashbook',
          templateUrl: './views/app/cashbook/cashbook.html',
          controller: 'cashbookCtrl',
      	resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    		'./scripts/services/form/formService.js',
                            './scripts/controllers/cashbook/cashbookCtrl.js'
                            ]
                  }]);
              }]
            }
      }).state('app.balance', {
	        abstract: true,
	        url: '/balance',
	        template: '<div ui-view=""></div>'
      }).state('app.balance.make',{
    	  url: '/make',
          templateUrl: './views/app/balance/balance.html',
          controller: 'balanceCtrl',
      	resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    		'./scripts/services/form/formService.js',
                            './scripts/controllers/balance/balanceCtrl.js'
                            ]
                  }]);
              }]
            }
      }).state('app.balance.view',{
    	  url: '/{balanceId:int}',
          templateUrl: './views/app/balance/balance.html',
          controller: 'balanceViewCtrl',
      	resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    		'./scripts/services/form/formService.js',
                            './scripts/controllers/balance/balanceViewCtrl.js'
                            ]
                  }]);
              }]
            }
      }).state('app.balance.list',{
    	  url: '/list',
          templateUrl: './views/app/balance/balanceList.html',
          controller: 'balanceListCtrl',
      	resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    		'./scripts/services/form/formService.js',
                            './scripts/controllers/balance/balanceListCtrl.js'
                            ]
                  }]);
              }]
            }
      }).state('app.income',{
    	  url: '/income',
          templateUrl: './views/app/schoolbudget/income.html',
          controller: 'incomeCtrl',
      	resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    		'./scripts/services/form/formService.js',
                            './scripts/controllers/schoolbudget/incomeCtrl.js'
                            ]
                  }]);
              }]
            }
      }).state('app.form', {
	        abstract: true,
	        url: '/form',
	        template: '<div ui-view=""></div>'
      }).state('app.form.nbudget',{
    	  url: '/nbudget',
          templateUrl: './views/app/form/nbudget.html',
          controller: 'nbudgetCtrl',
      	resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    		'./scripts/services/form/formService.js',
                            './scripts/controllers/form/nbudgetCtrl.js'
                            ]
                  }]);
              }]
            }
      }).state('app.form.loanfund',{
    	  url: '/loan',
          templateUrl: './views/app/form/nbudget.html',
          controller: 'loanCtrl',
      	resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    		'./scripts/services/form/formService.js',
                            './scripts/controllers/form/loanCtrl.js'
                            ]
                  }]);
              }]
            }
      }).state('app.form.subsidy',{
    	  url: '/subsidy',
          templateUrl: './views/app/form/nbudget.html',
          controller: 'subsidyCtrl',
      	resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    		'./scripts/services/form/formService.js',
                            './scripts/controllers/form/subsidyCtrl.js'
                            ]
                  }]);
              }]
            }
      }).state('app.form.insurance',{
    	  url: '/insurance',
          templateUrl: './views/app/form/nbudget.html',
          controller: 'insuranceCtrl',
      	resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    		'./scripts/services/form/formService.js',
                            './scripts/controllers/form/insuranceCtrl.js'
                            ]
                  }]);
              }]
            }
      }).state('app.form.fteacher',{
    	  url: '/fteacher',
          templateUrl: './views/app/form/nbudget.html',
          controller: 'fteacherCtrl',
      	resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    		'./scripts/services/form/formService.js',
                            './scripts/controllers/form/fteacherCtrl.js'
                            ]
                  }]);
              }]
            }
      }).state('app.form.expense',{
    	  url: '/expense',
          templateUrl: './views/app/form/nbudget.html',
          controller: 'expenseCtrl',
      	resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    		'./scripts/services/form/formService.js',
                            './scripts/controllers/form/expenseCtrl.js'
                            ]
                  }]);
              }]
            }
      }).state('app.form.education',{
    	  url: '/education',
          templateUrl: './views/app/form/nbudget.html',
          controller: 'educationCtrl',
      	resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    		'./scripts/services/form/formService.js',
                            './scripts/controllers/form/educationCtrl.js'
                            ]
                  }]);
              }]
            }
      }).state('app.form.tax',{
    	  url: '/tax',
          templateUrl: './views/app/form/nbudget.html',
          controller: 'taxCtrl',
      	resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    		'./scripts/services/form/formService.js',
                            './scripts/controllers/form/taxCtrl.js'
                            ]
                  }]);
              }]
            }
      }).state('app.form.revenue',{
    	  url: '/revenue',
          templateUrl: './views/app/form/nbudget.html',
          controller: 'revenueCtrl',
      	resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    		'./scripts/services/form/formService.js',
                            './scripts/controllers/form/revenueCtrl.js'
                            ]
                  }]);
              }]
            }
      }).state('app.form.contact',{
    	  url: '/contact',
          templateUrl: './views/app/form/nbudget.html',
          controller: 'contactCtrl',
      	resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    		'./scripts/services/form/formService.js',
                            './scripts/controllers/form/contactCtrl.js'
                            ]
                  }]);
              }]
            }
      }).state('app.form.scholarship',{
    	  url: '/scholarship',
          templateUrl: './views/app/form/nbudget.html',
          controller: 'scholarshipCtrl',
      	resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    		'./scripts/services/form/formService.js',
                            './scripts/controllers/form/scholarshipCtrl.js'
                            ]
                  }]);
              }]
            }
      }).state('app.form.healthcheck',{
    	  url: '/healthcheck',
          templateUrl: './views/app/form/nbudget.html',
          controller: 'healthcheckCtrl',
      	resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    		'./scripts/services/form/formService.js',
                            './scripts/controllers/form/healthcheckCtrl.js'
                            ]
                  }]);
              }]
            }
      }).state('app.user', {
	        url: '/user',
            templateUrl: './views/app/user/user.html',
            controller: 'userCtrl',
        	resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                  return $ocLazyLoad.load([{
                      files: [
                    	  	  './scripts/services/user/userService.js',
                              './scripts/controllers/user/userCtrl.js'
                              ]
                    }]);
                }]
              }
        }).state('app.user.role',{
	      	url: '/{userId:int}',
	      	onEnter:['$uibModal', '$state', '$stateParams', function($uibModal, $state, $stateParams){
	      		$uibModal.open({
	      			template : '<div ui-view="modal"></div>',
	      			size: 'lg'
	      		}).result.finally(function(){
	      			$state.go('app.user',{},{reload:true});
	      		});
	      	}],
	    	resolve: {
	            deps: ['$ocLazyLoad', function($ocLazyLoad) {
	              return $ocLazyLoad.load([{
	                  files: [	'./scripts/controllers/user/roleCtrl.js'
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
