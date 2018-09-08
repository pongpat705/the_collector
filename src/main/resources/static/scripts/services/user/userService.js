angular.module('app')
  .factory('userService', ['$http', '$rootScope', function UserService($http, $rootScope) {
    return {
    	getAllUser: function(page, size) {
            return $http.get(_CONTEXT+'/api/users',{params:{'page':page, 'size':size}});
        },
        getUserByName: function(page, size) {
        	return $http.get(_CONTEXT+'/api/users/search/findByUserNameContaining',{params:{'page':page, 'size':size}});
        },
        addUser: function(user){
        	return $http.post(_CONTEXT+'/service/addUser',user);
        },
        delUsers : function(id){
      	  return $http.patch(_CONTEXT+'/service/deleteUsers/'+id);
        },
        getUserForpatch : function(id){
    	  return $http.get(_CONTEXT+'/service/getUserForPatch/'+id);
        },
        patchUser : function(user, id){
        	return $http.patch(_CONTEXT+'/service/patchUser/'+id,user);
        },
        getAllActiveStudent: function(page, size) {
        	return $http.get(_CONTEXT+'/api/students/search/findByActive',{params:{'page':page, 'size':size, 'active':'Y'}});
        },
        uploadFile : function(file){
      	  var fd = new FormData();
            fd.append('uploadItem', file);
            return $http(
          		  {	url: CONTEXT+'/service/uploadFile/',
          			method: 'POST',
          			data: fd,
          			headers: { 'Content-Type': undefined},
          			transformRequest: angular.identity
          		  });
        }
    };
  }])