package Task5;
public class LicensePlateValidator {
    public static boolean validateLicensePlate(String plate) {
        return plate != null && plate.matches("[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}");
    }
}
