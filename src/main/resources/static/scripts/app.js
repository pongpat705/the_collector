'use strict';

/**
 * @ngdoc overview
 * @name app
 * @description
 * # app
 *
 * Main module of the application.
 */

angular
  .module('app', [
    'ui.router',
    'ngAnimate',
    'ui.bootstrap',
    'oc.lazyLoad',
    'ngStorage',
    'ngCookies',
    'ngSanitize',
    'ui.jq',
    'ngTouch',
    'pascalprecht.translate',
    'n3-line-chart',
    'textAngular',
//    'satellizer',
    'permission', 'permission.ui', 
    'restangular', 
    'toastr',
    'ngSnakeCamel',
    'ui.grid',
    'ui.grid.pagination',
    'ui.grid.resizeColumns',
    'ui.grid.autoFitColumns',
    'ngMaterial'
  ])
  .constant('COLORS', {
    font: 'Arial, "Helvetica Neue", Helvetica, sans-serif',
    'default': $('<div>').appendTo('body').addClass('bg-default').css('background-color'),
    primary: $('<div>').appendTo('body').addClass('bg-primary').css('background-color'),
    success: $('<div>').appendTo('body').addClass('bg-success').css('background-color'),
    warning: $('<div>').appendTo('body').addClass('bg-warning').css('background-color'),
    danger: $('<div>').appendTo('body').addClass('bg-danger').css('background-color'),
    info: $('<div>').appendTo('body').addClass('bg-info').css('background-color'),
    white: $('<div>').appendTo('body').addClass('bg-white').css('background-color'),
    dark: $('<div>').appendTo('body').addClass('bg-dark').css('background-color'),
    border: '#e4e4e4',
    bodyBg: $('body').css('background-color'),
    textColor: '#6B6B6B',
  })
  .run(['$http', '$rootScope', '$q', '$uibModalStack', '$state', 'PermissionStore', '$sessionStorage', 'Restangular',
        function($http, $rootScope, $q, $uibModalStack, $state, PermissionStore, $sessionStorage, Restangular) {
	  $rootScope.userData = _USER_DATA;
	  $rootScope.functions = _FUNCTION;
	  $rootScope.moneyControlForms = _MONEY_CONTROL_FORM;
	  
	  $rootScope.kv = {};
	  angular.forEach(_USER_DATA.authorities, function(value, key) {
		  var k = value.authority;
		  $rootScope.kv[k] = k;
	  });
	  
	  
	  $rootScope.balanceReport = _BALANCE_REPORT;
	  
	  $rootScope.mobilize = _MOBILIZE;
	  
	  $rootScope.dropdownBookbank = _DROPDOWN_BOOKBANK;
	  $rootScope.dropdownBalance = _DROPDOWN_BALANCE;
	  $rootScope.mapBookBank = _MAP_BOOKBANK;
	  $rootScope.mapBalance = _MAP_BALANCE;
	  
	  
	  $rootScope.$on('$stateChangeStart',  function(event, toState, toParams, fromState, fromParams, options){
		  $uibModalStack.dismissAll();
		  $rootScope.currentState = toState.name;
		  if(toState.name != fromState.name){
			  $rootScope.prevState = fromState.name;
		  }
		  if(undefined != $sessionStorage.roleList){
			  PermissionStore
				.defineManyPermissions($sessionStorage.roleList, function(permissionName, transitionProperties) {
					//FIXME
					return true;
			  });  
		  }
		  
	  });
	  
	  $rootScope.checkPermission = function(permission){
		  
		  if (null == $rootScope.kv[permission]) {
			  return false;
		  }
		  return true;
	  };
	  
	  $rootScope.isCurrentState = function(param1){
		  if ($rootScope.currentState == param1) {
			  return true;
		  } else {
			  return false;
		  }
	  };
	  
	  $rootScope.unAuthorized = function(response){
		  $uibModalStack.dismissAll();
		  $state.go('user.signout');
	  }
	  
	  $rootScope.initParams = function(){
		  var promises = [];
//		  promises.push( $http.get(_params.global.contextPath + '/api/data/pricing?size=100&sort=maxUser,asc' ).then(function(response){
//			  $rootScope.priceList = response.data._embedded.pricing;
//			  window.console && console.log('$rootScope.priceList  >>> ');
//			  window.console && console.log($rootScope.priceList);
//		  }) );	
		  
		  return $q.all(promises);
	  };
	  
    }]);

