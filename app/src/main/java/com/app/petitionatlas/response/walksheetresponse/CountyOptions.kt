import com.app.petitionatlas.response.walksheetresponse.CityOptions


class CountyOptions {
    private lateinit var cityOptions: List<CityOptions?>

    private lateinit var name: String

    fun getCityOptions(): List<CityOptions?>? {
        return cityOptions
    }

    fun setCityOptions(cityOptions: List<CityOptions?>) {
        this.cityOptions = cityOptions
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    override fun toString(): String {
        return "ClassPojo [cityOptions = $cityOptions, name = $name]"
    }


}