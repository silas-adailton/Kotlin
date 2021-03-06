package br.com.oliveira.silas.movies.data

import br.com.oliveira.silas.movies.data.mocks.MockMovies
import br.com.oliveira.silas.movies.data.remote.movie.RepositoryRemoteMovie
import br.com.oliveira.silas.movies.data.remote.movie.mapper.MovieMapper
import br.com.oliveira.silas.movies.data.retrofit.ServiceRetrofit
import br.com.oliveira.silas.movies.data.retrofit.ServiceRetrofitImpl
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryRemoteMovieTest {

    @Mock
    private lateinit var serviceRetrofitImpl: ServiceRetrofitImpl

    @Mock
    private lateinit var mapper: MovieMapper

    lateinit var repositoryRemoteMovie: RepositoryRemoteMovie

    @Before
    fun setUp() {
        repositoryRemoteMovie = RepositoryRemoteMovie(mapper, serviceRetrofitImpl)
    }


    @Test
    fun `Should return a list of movies from api when call is successful`() {
        val movieEntityResponse = MockMovies.getMovieEntityRemote()

        `when`(repositoryRemoteMovie.serviceRetrofitImpl.getMovies(anyString())).thenReturn(Maybe.just(movieEntityResponse))
        val result = repositoryRemoteMovie.serviceRetrofitImpl.getMovies(anyString())
                .test()

        result
                .assertNoErrors()
                .assertComplete()
                .assertValue(movieEntityResponse)
    }

    @Test
    fun `Should return an exception from api when call is unsuccessful`() {
        val exception = Exception()

        `when`(repositoryRemoteMovie.serviceRetrofitImpl.getMovies(anyString())).thenReturn(Maybe.error(exception))

        val result = repositoryRemoteMovie.serviceRetrofitImpl.getMovies(anyString()).test()

        result
                .assertNotComplete()
                .assertNoValues()
                .assertError(exception)
    }
}