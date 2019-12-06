import com.benhurqs.network.domain.model.Imovel
import com.benhurqs.network.domain.usecase.VivaRealUseCase

class TestVivaRealUseCase(list: List<Imovel>) : VivaRealUseCase(list){
    override fun rentalCondition(imovel: Imovel): Boolean {
        return super.rentalCondition(imovel)
    }

    override fun salesCondition(imovel: Imovel): Boolean {
        return super.salesCondition(imovel)
    }

    fun checkRentalCondition(imovel: Imovel): Boolean{
        return rentalCondition(imovel)
    }

    fun checkSalesCondition(imovel: Imovel): Boolean{
        return salesCondition(imovel)
    }

    fun checkRentalCondoFeeCondition(imovel: Imovel): Boolean {
        return rentalCondoFeeCondition(imovel)
    }

    fun checkRentalBoundingBoxCondition(imovel: Imovel): Boolean {
        return rentalBoundingBoxCondition(imovel)
    }
}