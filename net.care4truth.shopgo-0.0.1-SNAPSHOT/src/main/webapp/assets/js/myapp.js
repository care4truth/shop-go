$(function() {
	//solving active menu problem
	switch(menu) {
		case 'About Us':
			$('#about').addClass('active');
			break;
		case 'Contact Us':
			$('#contact').addClass('active');
			break;
		case 'All Products' :
			$('#listProducts').addClass('active');
			break;
		case 'Manage Products' :
			$('#manageProducts').addClass('active');
			break;
		default:
			if(menu == "Home") break;
			$('#listProducts').addClass('active');
			$('#a_'+menu).addClass('active');
			break;
	}
	//code for jquery dataTable
	// create a dataset
/*	var products = [
			['1','CBA'],
			['2','CYX'],
			['3','PQR'],
			['4','CDE'],
			['5','EFG'],
			['6','WVB'],
			['7','IJK'],
			['8','LMN'],
			['9','POQ'],
			['10','STU'],
			['11','QOR']
	];*/
	
	var $table = $('#productListTable');
	//exceute the below code only where we have this table
	if($table.length) {
		//console.log('Inside the table!');
		var jsonUrl = '';
		if(window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/' + window.categoryId + '/products'
		}
		$table.DataTable({
			lengthMenu : [[3,5,10,-1],['3 Records', '5 Records', '10 Records', 'ALL']],
			pageLength: 5,	
			ajax: {
				url:jsonUrl,
				dataSrc : ''
			},
			columns: [
				{
					data:'code',
					mRender:function(data,type,row){
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
					}
				},
				{
					data:'name',
				},
				{
					data : 'brand'
				}, 
				{
					data: 'unitPrice',
					mRender : function(data,type,row){
						return "$"+ data;
					}
				},
				{
					data : 'quantity',
					mRender : function(data , type ,row ){
						if (data < 1 ) {
							return '<span style="color:red">Out of stock!</span>';
						}
						return data;
					}
				},
				{
					data:'id',
					bSortable:false,
					mRender: function(data,type,row){
						var str = '';
						str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a>&#160';
						
						if(row.quantity < 1) {
							str += '<a href="javascript:void(0)" class="btn btn-succes disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						} else {
							str += '<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}
						
						return str;
					}
				}
			]
		});
	}
	
	//dismissing the alert after 3 seconds automatically 
	$alert = $('.alert');
	if($alert.length) {
		setTimeout(function(){
			$alert.fadeOut('slow');
		},3000);
	}
	//----------------------------------------------
	$('.switch input[type="checkbox"]').on('change',function(){
		var checkbox = $(this);
		var checked = checkbox.prop('checked');
		var dMsg = (checked) ? 'You want to activate the product?' : 'You want to deactivate the product?';
		var value = checkbox.prop('value');
		
		bootbox.confirm({
			size : 'medium',
			title : 'Product Activation & DeActivation',
			message : dMsg,
			callback : function(confirmed) {
				if(confirmed) {
					console.log(value);
					bootbox.alert({
						size:'medium',
						title : 'Information',
						message : 'You are going to perform operation on product' + value
					});
				} else {
					checkbox.prop('checked', !checked);
				}
			}
		});
	});
	// ---------------------------------
	// code for jquery admin data table 
	// ---------------------------------
	$adminProductsTable = $('#adminProductsTable');
	//exceute the below code only where we have this table
	if($adminProductsTable.length) {
		//console.log('Inside the table!');
		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
		
		$adminProductsTable.DataTable({
			lengthMenu : [ [10,30,50,-1], ['10 Records', '30 Records', '50 Records', 'ALL']] ,
			pageLength: 30,	
			ajax: {
				url:jsonUrl,
				dataSrc : ''
			},
			columns: [
						{	data : 'id'	}, 
						{
							data:'code',
							mRender:function(data,type,row){
									return '<img src="'+window.contextRoot+'/resources/images/'+ data +'.jpg" class="adminDataTableImg"/>';
							}
						},
						{
							data:'name',
						},
						{
							data : 'brand'
						}, 
						{
							data : 'quantity',
							mRender : function(data , type ,row ){
								if (data < 1 ) {
									return '<span style="color:red">Out of stock!</span>';
								}
								return data;
							}
						},
						{
							data: 'unitPrice',
							mRender : function(data,type,row){
								return '&#8377; '+ data;
							}
						},
						{
							data:'active',
							bSortable:false,
							mRender: function(data,type,row){
								var str = '';
								
								str += '<label class="switch">';
								if(data) {
									str += '<input type="checkbox" checked="checked" value="'+row.id+'" />';		
								} else {
									str += '<input type="checkbox" value="'+row.id+'" />';		
								}
								str += '<div class="slider"/></label>';
								
								return str;
							}
						}, 
						{
							data : 'id',
							bSortable : false , 
							mRender : function(data,type,row) {
								var str = '';
								str += '<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-primary">';
								str += '<span class="glyphicon glyphicon-pencil"></span></a>';
								return str;
							}
						}
					],
					
					// initcomplete - used to apply event handling after table load completion.Without this , it will not fire events like toggle switch
			initComplete : function() {
				var api = this.api();
				$('.switch input[type="checkbox"]').on('change',function(){
					var checkbox = $(this);
					var checked = checkbox.prop('checked');
					var dMsg = (checked) ? 'You want to activate the product?' : 'You want to deactivate the product?';
					var value = checkbox.prop('value');
					
					bootbox.confirm({
						size : 'medium',
						title : 'Product Activation & DeActivation',
						message : dMsg,
						callback : function(confirmed) {
							if(confirmed) {
								console.log(value);
								var activationUrl = window.contextRoot + '/manage/product/' + value + '/activation';
								$.post(activationUrl , function(data){
									bootbox.alert({
										size:'medium',
										title : 'Information',
										message : data
									});
								});
							} else {
								checkbox.prop('checked', !checked);
							}
						}
					});
				});
			}
		});
	}
	
	
});