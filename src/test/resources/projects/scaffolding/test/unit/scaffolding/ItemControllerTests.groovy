package scaffolding



import org.junit.*
import grails.test.mixin.*


@TestFor(ItemController)
@Mock(Item)
class ItemControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/item/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.itemInstanceList.size() == 0
        assert model.itemInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.itemInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.itemInstance != null
        assert view == '/item/create'

		params.title = 'The Item'

        controller.save()

        assert response.redirectedUrl == '/item/show/1'
        assert controller.flash.message != null
        assert Item.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/item/list'


        def item = new Item(title:"The Item")

        assert item.save() != null

        params.id = item.id

        def model = controller.show()

        assert model.itemInstance == item
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/item/list'


        def item = new Item(title:"The Item")

        assert item.save() != null

        params.id = item.id

        def model = controller.edit()

        assert model.itemInstance == item
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/item/list'

        response.reset()


        def item = new Item(title:"The Item")

        assert item.save() != null

        // test invalid parameters in update
        params.id = item.id
		params.title = ''

        controller.update()

        assert view == "/item/edit"
        assert model.itemInstance != null

        item.clearErrors()

		params.title = 'The Item'

        controller.update()

        assert response.redirectedUrl == "/item/show/$item.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/item/list'

        response.reset()

        def item = new Item(title:"The Item")

        assert item.save() != null
        assert Item.count() == 1

        params.id = item.id

        controller.delete()

        assert Item.count() == 0
        assert Item.get(item.id) == null
        assert response.redirectedUrl == '/item/list'


    }


}