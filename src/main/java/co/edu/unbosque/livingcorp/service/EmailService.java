package co.edu.unbosque.livingcorp.service;

import java.util.List;
import java.util.Properties;

import co.edu.unbosque.livingcorp.model.dto.*;
import co.edu.unbosque.livingcorp.model.exception.IncompleteNotificationException;
import jakarta.ejb.Stateless;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Stateless
public class EmailService {

    public void sendNotification(String to, String subject, String content) throws IncompleteNotificationException {

        String from = "inmobiliarialivingcorp@gmail.com";
        final String username = "inmobiliarialivingcorp@gmail.com";
        final String password = "fkif qtga fwex onki";


        String host = "smtp.gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new IncompleteNotificationException("No se pudo completar el envió la notificacion.");
        }
    }

    public void sendResourceBookingNotification(UserDTO user, ResourceBookingDTO resourceBooking) throws IncompleteNotificationException {
        String resourceName = resourceBooking.getPropertyResourceId().getResId().getResourceType();
        String startDate = resourceBooking.getBookingStartDate().toString();
        String endDate = resourceBooking.getBookingEndDate().toString();
        double cost = resourceBooking.getBookingCost();

        String to = user.getUserEmail();
        String subject = "Confirmación de Reserva | Living Corp";

        String content = "Hola, " + user.getUserName() + ",\n\n" +
                "Tu reserva ha sido confirmada para el recurso " + resourceName + " de la propiedad " + resourceBooking.getPropertyResourceId().getProId().getPropertyName() + ".\n\n" +
                "Factura de Pago:\n\n" +
                "  Fecha y Hora de Inicio: " + startDate + "\n" +
                "  Fecha y Hora de Fin: " + endDate + "\n" +
                "  Costo: $" + String.format("%.2f", cost) + "\n\n" +
                "Atentamente,\n\n" +
                "Equipo de Soporte de Living Corp.";

        sendNotification(to, subject, content);
    }

    public void sendResidentsNotification(PropertyResourceDTO propResource) throws IncompleteNotificationException {

        List<ResidentDTO> residents = propResource.getProId().getResidents();
        for(ResidentDTO resident : residents){
            String to = resident.getUserName().getUserEmail();
            String subject = "Creación de nuevo Recurso | Living Corp";

            String content = "Hola, " + resident.getUserName().getUserName() + ",\n\n" +
                    "Te informamos la apertura del nuevo recurso " + propResource.getResId().getResourceType() + " en tu actual residencia " + propResource.getProId().getPropertyName() + ".\n\n" +
                    "Detalles del Nuevo Recurso:\n\n" +
                    "  Descipción: " + propResource.getResId().getResourceDescription() + "\n" +
                    "  Tiempo minimo para reservar (Horas): " + propResource.getMinTimeH() + "\n" +
                    "  Costo minimo para reservar: $" + String.format("%.2f", propResource.getMinPrice()) + "\n\n" +
                    "Ingresa a nuestra página oficial para saber más detalles. ¿Qué esperas para conocerlo?\n\n" +
                    "Atentamente,\n\n" +
                    "Equipo de Soporte de Living Corp.";

            sendNotification(to, subject, content);
        }
    }

    public void sendQuotationNotificationToProvider(UserDTO user, ServiceQuotationDTO serviceQuotation) throws IncompleteNotificationException {
        String to = serviceQuotation.getSvcProviderId().getProviderEmail();
        String subject = "Solicitud de Cotización | Living Corp";

        String content = "Estimados Proveedores,\n\n" +
                "Por este medio, deseamos solicitar de manera formal una cotización para los servicios de " + serviceQuotation.getSvcProviderId().getServiceType() + ".\n\n" +
                "Detalles de la solicitud de cotización:\n" +
                "- Cliente: " + user.getUserName() + "\n" +
                "- Propiedad: " + serviceQuotation.getPropertyId().getPropertyName() + "\n" +
                "- Fecha y Hora de la solicitud: " + serviceQuotation.getRfqDateTime() + "\n" +
                "- Descripción de la petición: " + serviceQuotation.getRequestDescription() + "\n\n" +
                "Por favor, revisen esta solicitud y envíen su cotización correspondiente.\n\n" +
                "Atentamente,\n\n" +
                "Equipo de Soporte de Living Corp.";

        sendNotification(to, subject, content);
    }

    public void sendQuotationNotificationToResident(UserDTO user, ServiceQuotationDTO serviceQuotation) throws IncompleteNotificationException {
        String to = user.getUserEmail();
        String subject = "Cotización del Servicio de " + serviceQuotation.getSvcProviderId().getServiceType() + " | Living Corp";

        String content = "Estimado(a) " + user.getUserName() + ",\n\n" +
                "Hemos recibido tu solicitud de cotización para el servicio de " + serviceQuotation.getSvcProviderId().getServiceType() + " en la propiedad " + serviceQuotation.getPropertyId().getPropertyName() + ".\n\n" +
                "Detalles de la cotización:\n" +
                "- Fecha y Hora de la solicitud: " + serviceQuotation.getRfqDateTime() + "\n" +
                "- Descripción de la petición: " + serviceQuotation.getRequestDescription() + "\n" +
                "- Costo estimado: $" + String.format("%.2f", serviceQuotation.getSvcProviderId().getServicePrice()) + "\n\n" +
                "Te mantendremos informado(a) sobre el progreso de tu solicitud.\n\n" +
                "Atentamente,\n\n" +
                "Equipo de Soporte de Living Corp.";

        sendNotification(to, subject, content);
    }

    public void sendServiceConfirmationToResident(UserDTO user, ServiceRequestDTO serviceRequest) throws IncompleteNotificationException {
        String to = user.getUserEmail();
        String subject = "Confirmación de Servicio | Living Corp";

        String content = "Estimado(a) " + user.getUserName() + ",\n\n" +
                "Nos complace informarte que tu solicitud de servicio ha sido confirmada con éxito para la propiedad " + serviceRequest.getPropertyId().getPropertyName() + ".\n\n" +
                "Detalles de la solicitud de servicio:\n" +
                "- Fecha y Hora de la solicitud: " + serviceRequest.getRqstDateTime() + "\n" +
                "- Descripción de la petición: " + serviceRequest.getRequestDescription() + "\n" +
                "- Proveedor seleccionado: " + serviceRequest.getSvcProviderId().getServiceDescription() + "\n" +
                "- Fecha y Hora del servicio: " + serviceRequest.getSvcDateTime() + "\n\n" +
                "Recibirás más información sobre el servicio próximamente.\n\n" +
                "Atentamente,\n\n" +
                "Equipo de Soporte de Living Corp.";

        sendNotification(to, subject, content);
    }

}
