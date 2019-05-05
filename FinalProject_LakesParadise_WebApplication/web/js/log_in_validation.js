$(document).ready(function() {
    $('#log_in_form').bootstrapValidator({
        container: '#navbarMessage',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            login: {
                validators: {
                    notEmpty: {
                        message: 'The login is required and cannot be empty'
                    },
                    stringLength: {
                        min: 2,
                        max: 45,
                        message: 'Please enter in login field at least 2 characters and no more than 45'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and cannot be empty'
                    },
                    stringLength: {
                        min: 5,
                        max: 45,
                        message: 'Please enter in password field at least 5 characters and no more than 45'
                    }
                }
            }
        }
    });
});