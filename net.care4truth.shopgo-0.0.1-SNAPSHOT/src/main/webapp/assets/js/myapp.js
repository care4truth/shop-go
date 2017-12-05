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
		default:
			if(menu == "Home") break;
			$('#listProducts').addClass('active');
			$('#a_'+menu).addClass('active');
			break;
	}
	//code for jquery dataTable
	// create a dataset
	var products = [
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
	];
	
	var $table = $('#productListTable');
	//exceute the below code only where we have this table
	if($table.length) {
		console.log('Inside the table!');
		$table.DataTable({
			lengthMenu : [[3,5,10,-1],['3 Records', '5 Records', '10 Records', 'ALL']],
			pageLength: 5,	
			data : products
		});
	}
});