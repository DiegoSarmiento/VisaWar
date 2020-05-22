<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TIENDA VIRTUAL INDECOPI</title>
</head>

<body>
	<img alt="Qries" src="https://www.indecopi.gob.pe/image/layout_set_logo?img_id=3224595&t=1585113168475" width="213" height="62">
	<br>
	<br>
	<div style="font: small/1.5 Arial,Helvetica,sans-serif;">
		<div>Estimado(a) señor(a),</div><br>
		<div>Hemos recibido y verificado su pago en la TIENDA VIRTUAL INDECOPI.</div>
		<div>Procederemos con el despacho de vuestro pedido número ${num_pedido} por S/ ${total_pedido} Soles.</div><br>
		<div>Esta compra fue realizada hoy con los siguientes datos:</div>
		<ul>
			<li><b>Usuario con correo: </b>${email}</li>
			<li><b>Número de pedido: </b>${num_pedido}</li>
			<li><b>Dirección IP: </b>${num_ip}</li>
		</ul>
		<div>Datos de facturación:</div>
		<ul>
			<li><b>Documento: </b>VOUCHER</li>
			<li><b>Importe de la transacción: </b>${total_pedido}</li>
			<li><b>Moneda: </b>SOL</li>
		</ul>

		<div>Observaciones:</div>
		<div>Detalle de la(s) Solicitud(es):</div>
		<br>
		<div>MARCA DE PRODUCTO ( ${total_pedido} )</div>
		<div>Detalle de los productos:</div>

	</div>

	<table style="border: 1px black solid;" width="70%">
		<tr>
		  	<th style="border: 1px black solid;"  width="60%">Nombre</th>
		  	<th style="border: 1px black solid;"  width="10%">Precio</th> 
		  	<th style="border: 1px black solid;"  width="10%">Cantidad</th>
			<th style="border: 1px black solid;"  width="20%">Subtotal</th>
		</tr>
		<#list detalles as detalle>
			<tr>
				<td  style="border: 1px black solid;">${detalle[2]}</td>
				<td  style="border: 1px black solid; text-align:right;">S/ ${detalle[3]}</td>
				<td  style="border: 1px black solid; text-align:right;">${detalle[4]}</td>
				<td  style="border: 1px black solid; text-align:right;">S/ ${detalle[5]}</td>
			</tr>
		</#list>
		<tr>
		  	<td colspan="3"  style="border: 1px black solid;">Subtotal</td>
		  	<td  style="border: 1px black solid; text-align:right;">S/ ${total_pedido}</td>
		</tr>
		<tr>
			<td colspan="3"  style="border: 1px black solid;">Flete</td>
			<td style="border: 1px black solid; text-align:right;">S/ 0.0</td>
		</tr>
		<tr>
			<td colspan="3" style="border: 1px black solid;">Total</td>
			<td style="border: 1px black solid; text-align:right;">S/ ${total_pedido}</td>
		</tr>
	  </table>

		<div>Muchas gracias por visitar nuestra tienda virtual.</div>
		<div>Atentamente</div>
		<div>TIENDA VIRTUAL INDECOPI</div>

	<div style="font-size: 9; font-family: Arial, Helvetica, sans-serif; font-style: italic;">
		<p>Los datos personales proporcionados se mantendrán almacenados mientras su uso y tratamiento sean necesarios para cumplir con las finalidades anteriormente descritas.</p>
		<p>Se informa que el Indecopi podría compartir y/o usar y/o almacenar y/o transferir dicha información a terceras personas domiciliadas en el Perú o el extranjero, siempre que sean parte del servicio brindado, estrictamente con el objeto de realizar las actividades antes mencionadas.</p>
		<p>Se informa también que sus datos personales solo serán requeridos en aquellos casos en que resulten indispensables para el inicio de un servicio o gestión de reclamos, por lo que ante la negativa a brindarlos Indecopi no podrá atender lo solicitado.</p>
		<p>Usted podrá ejercer sus derechos de información, acceso, rectificación, cancelación y oposición de sus datos personales, en cualquier momento, a través de las mesas de partes de las oficinas del Indecopi</p>
	</div>
</body>
</html>