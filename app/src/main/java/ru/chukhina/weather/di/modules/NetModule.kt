package ru.chukhina.weather.di.modules

import androidx.viewbinding.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.chukhina.weather.data.api.OpenMeteoApi
import ru.chukhina.weather.data.api.OpenWeatherApi
import ru.chukhina.weather.di.qualifiers.ApiKey
import ru.chukhina.weather.di.qualifiers.ApiLang
import ru.chukhina.weather.di.qualifiers.ApiTimeFormat
import ru.chukhina.weather.di.qualifiers.ApiTimezone
import ru.chukhina.weather.di.qualifiers.ApiUnits
import ru.chukhina.weather.di.qualifiers.Logger
import ru.chukhina.weather.di.qualifiers.OpenMeteoApiClient
import ru.chukhina.weather.di.qualifiers.OpenWeatherApiClient

private const val OPEN_WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/"
private const val OPEN_METEO_BASE_URL = "https://api.open-meteo.com/v1/"

private const val API_KEY_KEY = "appid"
private const val API_KEY_VALUE = "251c40b92d5a3380ef4a0836a8dd088a"

private const val API_UNITS_KEY = "units"
private const val API_UNITS_VALUE = "metric"

private const val API_LANG_KEY = "lang"
private const val API_LANG_VALUE = "en"

private const val API_TIME_FORMAT_KEY = "timeformat"
private const val API_TIME_FORMAT_VALUE = "unixtime"

private const val API_TIMEZONE_KEY = "timezone"
private const val API_TIMEZONE_VALUE = "Europe%2FMoscow"

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Provides
    @ApiKey
    fun providesApiKeyInterceptor() = Interceptor { chain ->
        val original = chain.request()
        val newURL = original.url.newBuilder()
            .addQueryParameter(API_KEY_KEY, API_KEY_VALUE)
            .build()

        chain.proceed(
            original.newBuilder()
                .url(newURL)
                .build()
        )
    }

    @Provides
    @ApiUnits
    fun providesApiUnitsInterceptor() = Interceptor { chain ->
        val original = chain.request()
        val newURL = original.url.newBuilder()
            .addQueryParameter(API_UNITS_KEY, API_UNITS_VALUE)
            .build()

        chain.proceed(
            original.newBuilder()
                .url(newURL)
                .build()
        )
    }

    @Provides
    @ApiLang
    fun providesApiLangInterceptor() = Interceptor { chain ->
        val original = chain.request()
        val newURL = original.url.newBuilder()
            .addQueryParameter(API_LANG_KEY, API_LANG_VALUE)
            .build()

        chain.proceed(
            original.newBuilder()
                .url(newURL)
                .build()
        )
    }

    @Provides
    @ApiTimezone
    fun providesApiTimezoneInterceptor() = Interceptor { chain ->
        val original = chain.request()
        val newURL = original.url.newBuilder()
            .addQueryParameter(API_TIMEZONE_KEY, API_TIMEZONE_VALUE)
            .build()

        chain.proceed(
            original.newBuilder()
                .url(newURL)
                .build()
        )
    }

    @Provides
    @ApiTimeFormat
    fun providesApiTimeFormatInterceptor() = Interceptor { chain ->
        val original = chain.request()
        val newURL = original.url.newBuilder()
            .addQueryParameter(API_TIME_FORMAT_KEY, API_TIME_FORMAT_VALUE)
            .build()

        chain.proceed(
            original.newBuilder()
                .url(newURL)
                .build()
        )
    }

    @Provides
    @Logger
    fun providesLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
    }

    @Provides
    @OpenWeatherApiClient
    fun providesOpenWeatherOkhttp(
        @ApiKey apiKeyInterceptor: Interceptor,
        @ApiUnits apiUnitsInterceptor: Interceptor,
        @ApiLang apiLangInterceptor: Interceptor,
        @Logger loggingInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(apiUnitsInterceptor)
            .addInterceptor(apiLangInterceptor)
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(loggingInterceptor)
                }
            }
            .build()

    @Provides
    @OpenMeteoApiClient
    fun providesOpenMeteoOkhttp(
        @ApiTimezone timezoneInterceptor: Interceptor,
        @ApiTimeFormat timeFormatInterceptor: Interceptor,
        @Logger loggingInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(timeFormatInterceptor)
            .addInterceptor(timezoneInterceptor)
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(loggingInterceptor)
                }
            }
            .build()

    @Provides
    fun providesGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun providesOpenWeatherApi(
        @OpenWeatherApiClient okHttpClient: OkHttpClient,
        gsonConverter: GsonConverterFactory
    ): OpenWeatherApi =
        Retrofit.Builder()
            .baseUrl(OPEN_WEATHER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverter)
            .build()
            .create(OpenWeatherApi::class.java)

    @Provides
    fun providesOpenMeteoApi(
        @OpenMeteoApiClient okHttpClient: OkHttpClient,
        gsonConverter: GsonConverterFactory
    ): OpenMeteoApi =
        Retrofit.Builder()
            .baseUrl(OPEN_METEO_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverter)
            .build()
            .create(OpenMeteoApi::class.java)
}
