'use strict';
angular
	.module('app')
		.controller('roleCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', '$auth', 
									'$state' , '$stateParams', 'Restangular', 
									'toastr', '$rootScope',
  function roleCtrl(	$scope, $http, $localStorage, 
		  				$timeout, $translate, $auth, 
		  				$state, $stateParams, Restangular, 
		  				toastr, $rootScope) {
	
	$scope.$watch("init", function(){
	});
	
	
	$scope.ok = function () {
		$state.go('app.user',{},{reload:true});
	};
	
	$scope.cancel = function () {
		$state.go('app.user',{},{reload:true});
	};	
	
  }
]);