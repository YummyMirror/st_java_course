package ru.anatoli.sandbox;

import net.webservicex.GetCitiesByCountry;
import net.webservicex.GetWeather;
import net.webservicex.GlobalWeather;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by anatoli.anukevich on 6/3/2017.
 */
public class GlobalWeatherTests {
    @Test
    public void globalWeatherTests() {
        String country = new GlobalWeather().getGlobalWeatherSoap12().getCitiesByCountry("Germany");
        assertTrue(country.contains("Berlin-Schoenefel"));
    }
}
