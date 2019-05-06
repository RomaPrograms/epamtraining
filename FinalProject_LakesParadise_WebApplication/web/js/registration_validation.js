$(document).ready(function() {
    $('#reg_form').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            startDate: {
                validators: {
                    notEmpty: {
                        message: 'The login is required and cannot be empty'
                    }
                }
            },
            endDate: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and cannot be empty'
                    }
                }
            }
        }
    });
});