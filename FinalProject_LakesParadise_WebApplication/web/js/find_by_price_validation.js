$(document).ready(function() {
    $('#find_by_price_form').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            minPrice: {
                validators: {
                    regexp: {
                        regexp: /^[0-9]{0,10}((\.[0-9]{0,3}))?$/,
                        message: 'Please, follow pattern: ##########.### #-unnecessary numeral.'
                    }
                }
            },
            maxPrice: {
                validators: {
                    regexp: {
                        regexp: /^[0-9]{0,10}((\.[0-9]{0,3}))?$/,
                        message: 'Please, follow pattern: ##########.###  #-unnecessary numeral.'
                    }
                }
            }
        }
    });
});